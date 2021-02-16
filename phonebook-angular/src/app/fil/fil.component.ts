import { Component, OnInit } from '@angular/core';
import { PhoneService } from '../phone.service';
import { Fil, OrgFil } from './Fil';

@Component({
  selector: 'app-fil',
  templateUrl: './fil.component.html',
  styleUrls: ['./fil.component.css']
})
export class FilComponent implements OnInit {

  fils: filial[] = [];
  orgFil: OrgFil = new OrgFil();
  fil: Fil = new Fil();
  id:number = 0;
  idOrg: number = 0;
  done: boolean = false;

  constructor(
    private phoneService: PhoneService
  ) { }

  ngOnInit(): void {
    this.getFilsTbl();
  }

  getFilsTbl() {
    this.phoneService.getFils().subscribe((req) => {
      this.fils = req;
      console.log(req);
    });
  }

  setNewFilial(fil: Fil){
    this.phoneService.setNewFil(fil).subscribe(
                    (data: Fil) => {this.fil=data; this.done=true;},
                    error => console.log(error)
                    );
  }

  removeFilial(id: number){
    this.phoneService.removeFil(id).subscribe(
                    error => console.log(error)
                    );
  }

  updateFilial(filId:number, fil: Fil){
    this.phoneService.updateFil(filId, fil).subscribe(
                      error => console.log(error)
                  );
  }

  findFilialByNameFil(nameFil:string){
    this.phoneService.findFilByFilName(nameFil).subscribe((req) => {
      this.fils = req;
      console.log(req);
    });
  }

  setOrgToFilial(orgId:number, filId:number, fil: Fil){
    this.phoneService.setOrgToFil(orgId, filId, fil).subscribe(
                      error => console.log(error)
                  );
  }

  removeOrgFromFilial(orgId:number, filId:number){
    this.phoneService.removeOrgFromFil(orgId, filId).subscribe(
                      error => console.log(error)
                  );
  }
}

export interface filial{
  id:number,
  filialName?: string;
  address?: string;
  plainOrgDto?: orgFilial;
}

export interface orgFilial{
      nameOrg?:string;
}
