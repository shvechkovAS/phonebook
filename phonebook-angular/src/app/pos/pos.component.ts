import { Component, OnInit } from '@angular/core';
import { PhoneService } from '../phone.service';
import { Pos, EmpPos } from './Pos';

@Component({
  selector: 'app-pos',
  templateUrl: './pos.component.html',
  styleUrls: ['./pos.component.css']
})
export class PosComponent implements OnInit {

  poss:position[]= [];
  empPos:EmpPos = new EmpPos();
  plainEmployeesDto:EmpPos[] = [];
  pos:Pos = new Pos();
  id:number = 0;
  idEmp: number = 0;
  done: boolean = false;

  constructor(
    private phoneService: PhoneService
  ) {}

  ngOnInit(): void {
    this.getPossTbl();
  }

  getPossTbl() {
    this.phoneService.getPoss().subscribe((req) => {
      this.poss = req;
      console.log(req);
    });
  }

  setNewPosition(pos: Pos){
    this.phoneService.setNewPos(pos).subscribe(
                    (data: Pos) => {this.pos=data; this.done=true;},
                    error => console.log(error)
                    );
  }

  removePosition(id: number){
    this.phoneService.removePos(id).subscribe(
                    error => console.log(error)
                    );
  }

  updatePosition(posId:number, pos: Pos){
    this.phoneService.updatePos(posId, pos).subscribe(
                      error => console.log(error)
                  );
  }

  findPosByName(name:string){
    this.phoneService.findPosByName(name).subscribe((req) => {
      this.poss = req;
      console.log(req);
    });
  }

  setEmpToPosition(empId:number, id:number, pos:Pos){
    this.phoneService.setEmpToPos(empId, id, pos).subscribe(
                      error => console.log(error)
                  );
  }

  removeEmpFromPosition(empId:number, id:number){
    this.phoneService.removeEmpFromPos(empId, id).subscribe(
                      error => console.log(error)
                  );
  }
}

interface position{
  id:number;
  namePosition:string;
  salary?:number;
  countEmployees?:number;
  plainEmployeesDto:[empPosition];
  }

interface empPosition{
    fio?:string;
}
