import { Component, OnInit } from '@angular/core';
import { PhoneService } from '../phone.service';
import { Emp, DepEmp, OrgEmp } from './Emp';

@Component({
  selector: 'app-emp',
  templateUrl: './emp.component.html',
  styleUrls: ['./emp.component.css']
})
export class EmpComponent implements OnInit {

  emps:employee[]= [];
  orgEmp: OrgEmp = new OrgEmp();
  emp: Emp = new Emp();
  id:number = 0;
  idOrg: number = 0;
  idDep: number = 0;
  done: boolean = false;

  constructor(
    private phoneService: PhoneService
  ) { }

  ngOnInit(): void {
      this.getEmpsTbl();
  }

  getEmpsTbl() {
    this.phoneService.getEmps().subscribe((req) => {
      this.emps = req;
      console.log(req);
    });
  }

  setNewEmployee(emp: Emp){
    this.phoneService.setNewEmp(emp).subscribe(
                    (data: Emp) => {this.emp=data; this.done=true;},
                    error => console.log(error)
                    );
  }

  removeEmployee(id: number){
    this.phoneService.removeEmp(id).subscribe(
                    error => console.log(error)
                    );
  }

  updateEmployee(empId:number, emp: Emp){
    this.phoneService.updateEmp(empId, emp).subscribe(
                      error => console.log(error)
                  );
  }

  findEmpsBySecondName(secondName:string){
    this.phoneService.findEmpsBySecondName(secondName).subscribe((req) => {
      this.emps = req;
      console.log(req);
    });
  }

  setDepToEmployee(depId:number, empId:number, emp: Emp){
    this.phoneService.setDepToEmp(depId, empId, emp).subscribe(
                      error => console.log(error)
                  );
  }

  removeDepFromEmployee(depId:number, empId:number){
    this.phoneService.removeDepFromEmp(depId, empId).subscribe(
                      error => console.log(error)
                  );
  }

  setOrgToEmployee(orgId:number, empId:number, emp: Emp){
    this.phoneService.setOrgToEmp(orgId, empId, emp).subscribe(
                      error => console.log(error)
                  );
  }

  removeOrgFromEmployee(orgId:number, empId:number){
    this.phoneService.removeOrgFromEmp(orgId, empId).subscribe(
                      error => console.log(error)
                  );
  }
}

interface employee{
  id?:number;
  secondName?:string;
  firstName?:string;
  surname?:string;
  phoneNumber?:string;
  birthDate?:string;
  email?:string;
  gender?:string;
  genderS?:string;
  plainDepartmentDto?:depEmployee;
  plainOrgDto?:orgEmployee;
  }

interface depEmployee{
    depName:string;
}

interface orgEmployee{
    nameOrg:string;
}
