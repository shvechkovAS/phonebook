import { Component, OnInit } from '@angular/core';
import { PhoneService } from '../phone.service';
import { Org } from './Org';

@Component({
  selector: 'app-org',
  templateUrl: './org.component.html',
  styleUrls: ['./org.component.css']
})
export class OrgComponent implements OnInit {

  orgs:organization[] = [];
  id:number = 0;
  nameOrg:string = "";
  inn:string = "";
  org: Org = new Org();
  done: boolean = false;
  
  constructor(
    private phoneService: PhoneService
  ) { }

  ngOnInit(): void {
    this.getOrgsTbl();
  }

  getOrgsTbl() {
    this.phoneService.getOrgs().subscribe((req) => {
      this.orgs = req;
      console.log(req);
    });
  }

  findOrganizationByInn(inn:string){
    this.phoneService.findOrgByInn(inn).subscribe((req) => {
      this.orgs = req;
      console.log(req);
    });
  }

  findOrganizationByNameOrg(nameOrg:string){
    this.phoneService.findOrgByNameOrg(nameOrg).subscribe((req) => {
      this.orgs = req;
      console.log(req);
    });
  }

  setNewOrganization(org: Org){
    this.phoneService.setNewOrg(org).subscribe(
                    (data: Org) => {this.org=data; this.done=true;},
                    error => console.log(error)
                  );
  }

  removeOrganization(id:number){
    this.phoneService.removeOrg(id).subscribe(
                    error => console.log(error)
                  );
  }

  updateOrganization(id:number,org: Org){
    this.phoneService.updateOrg(id, org).subscribe(
                      error => console.log(error)
                  );
  }
}

interface organization{
  id:number,
  nameOrg?:string,
  directorName?:string,
  inn?:string,
  kpp?:string,
  address?:string
}
