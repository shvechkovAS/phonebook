import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Org } from './org/Org';
import { Dep, FilBranchDep, OrgDep } from './dep/Dep';
import { Fil, OrgFil } from './fil/Fil';
import { Emp, DepEmp, OrgEmp } from './emp/Emp';
import { Pos, EmpPos } from './pos/Pos';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  constructor(private http: HttpClient) { }
/*Организация*/
  getOrgs(): Observable<any>{
    return this.http.get("http://localhost:8080/organization");
  }

  setNewOrg(org: Org): Observable<any>{
    const body: Org = {
      nameOrg:org.nameOrg,
      directorName:org.directorName,
      inn:org.inn,
      kpp:org.kpp,
      address:org.address
    }
    return this.http.post(`http://localhost:8080/organization`, body);
  }

  findOrgById(id:number): Observable<any>{
    return this.http.get(`http://localhost:8080/organization/`+id);
  }

  findOrgByInn(inn:string): Observable<any>{
    return this.http.get(`http://localhost:8080/organization/inn/`+inn);
  }

  findOrgByNameOrg(name:string): Observable<any>{
    return this.http.get(`http://localhost:8080/organization/name/`+name);
  }

  updateOrg(id: number, org: Org): Observable<any>{
    const body: Org = {
      nameOrg:org.nameOrg,
      directorName:org.directorName,
      inn:org.inn,
      kpp:org.kpp,
      address:org.address
    }
    return this.http.put(`http://localhost:8080/organization/`+id, body);
  }

  removeOrg(id: number): Observable<any>{
    return this.http.delete(`http://localhost:8080/organization/`+id);
  }
/*Отдел*/
  getDeps(): Observable<any>{
    return this.http.get("http://localhost:8080/department");
  }

  setNewDep(dep: Dep): Observable<any>{
    const body: Dep = {
      depName:dep.depName,
      plainFilialBranchDto:{} as FilBranchDep,
      plainOrgDto:{} as OrgDep
      }
    return this.http.post("http://localhost:8080/department", body);
  }

  updateDep(id: number, dep: Dep): Observable<any>{
    const body: Dep = {
      depName:dep.depName,
      plainFilialBranchDto:{} as FilBranchDep,
      plainOrgDto:{} as OrgDep
      }
    return this.http.put(`http://localhost:8080/department/`+id, body);
  }

  removeDep(id: number){
    return this.http.delete(`http://localhost:8080/department/`+id);
  }

  findDepByDepName(name:string): Observable<any>{
    return this.http.get(`http://localhost:8080/department/depName/`+name);
  }

  findDepById(id:number): Observable<any>{
    return this.http.get(`http://localhost:8080/department/`+id);
  }

  setOrgToDep(orgId:number, depId:number, dep: Dep): Observable<any>{
    const body: Dep = {
      depName:dep.depName,
      plainFilialBranchDto:{} as FilBranchDep,
      plainOrgDto:{} as OrgDep
      }
    return this.http.post(`http://localhost:8080/organization/`+orgId+`/department/`+depId+`/add`, body);
  }

  removeOrgFromDep(orgId:number, depId:number): Observable<any>{
    return this.http.delete(`http://localhost:8080/organization/`+orgId+`/department/`+depId+`/remove`);
  }

  setFilToDep(filId:number, depId:number, dep: Dep): Observable<any>{
    const body: Dep = {
      depName:dep.depName,
      plainFilialBranchDto:{} as FilBranchDep,
      plainOrgDto:{} as OrgDep
      }
    return this.http.post(`http://localhost:8080/filialBranch/`+filId+`/department/`+depId+`/add`, body);
  }

  removeFilFromDep(filId:number, depId:number): Observable<any>{
    return this.http.delete(`http://localhost:8080/filialBranch/`+filId+`/department/`+depId+`/remove`);
  }

  /*Филиал*/
  getFils(): Observable<any>{
    return this.http.get("http://localhost:8080/filialBranch");
  }

  setNewFil(fil: Fil): Observable<any>{
    const body: Fil = {
      filialName:fil.filialName,
      address:fil.address,
      plainOrgDto:{} as OrgFil
      }
    return this.http.post("http://localhost:8080/filialBranch", body);
  }

  updateFil(id: number, fil: Fil): Observable<any>{
    const body: Fil = {
      filialName:fil.filialName,
      address:fil.address,
      plainOrgDto:{} as OrgFil
      }
    return this.http.put(`http://localhost:8080/filialBranch/`+id, body);
  }

  removeFil(id: number){
    return this.http.delete(`http://localhost:8080/filialBranch/`+id);
  }

  findFilByFilName(name:string): Observable<any>{
    return this.http.get(`http://localhost:8080/filialBranch/name/`+name);
  }

  findFilById(id:number): Observable<any>{
    return this.http.get(`http://localhost:8080/filialBranch/`+id);
  }

  setOrgToFil(orgId:number, filId:number, fil: Fil): Observable<any>{
    const body: Fil = {
      filialName:fil.filialName,
      address:fil.address,
      plainOrgDto:{} as OrgFil
      }
    return this.http.post(`http://localhost:8080/organization/`+orgId+`/filialBranch/`+filId+`/add`, body);
  }

  removeOrgFromFil(orgId:number, filId:number): Observable<any>{
    return this.http.delete(`http://localhost:8080/organization/`+orgId+`/filialBranch/`+filId+`/remove`);
  }


  /*Сотрудник*/
  getEmps(): Observable<any>{
    return this.http.get("http://localhost:8080/employee");
  }

  setNewEmp(emp: Emp): Observable<any>{
    const body: Emp = {
        secondName:emp.secondName,
        firstName:emp.firstName,
        surname:emp.surname,
        phoneNumber:emp.phoneNumber,
        birthDate:emp.birthDate,
        email:emp.email,
        gender:emp.gender,
        genderS:emp.genderS,
        department:{} as  DepEmp,
        organization:{} as OrgEmp
        }
    return this.http.post("http://localhost:8080/employee", body);
  }

  updateEmp(id: number, emp: Emp): Observable<any>{
    const body: Emp = {
        secondName:emp.secondName,
        firstName:emp.firstName,
        surname:emp.surname,
        phoneNumber:emp.phoneNumber,
        birthDate:emp.birthDate,
        email:emp.email,
        gender:emp.gender,
        genderS:emp.genderS,
        department:{} as  DepEmp,
        organization:{} as OrgEmp
        }
    return this.http.put(`http://localhost:8080/employee/`+id, body);
  }

  removeEmp(id: number){
    return this.http.delete(`http://localhost:8080/employee/`+id);
  }

  findEmpsBySecondName(name:string): Observable<any>{
    return this.http.get(`http://localhost:8080/employee/secondName/`+name);
  }

  findEmpById(id:number): Observable<any>{
    return this.http.get(`http://localhost:8080/employee/`+id);
  }

  setDepToEmp(depId:number, empId:number, emp: Emp): Observable<any>{
    const body: Emp = {
      secondName:emp.secondName,
      firstName:emp.firstName,
      surname:emp.surname,
      phoneNumber:emp.phoneNumber,
      birthDate:emp.birthDate,
      email:emp.email,
      gender:emp.gender,
      genderS:emp.genderS,
      department:{} as  DepEmp,
      organization:{} as OrgEmp
        }
    return this.http.post(`http://localhost:8080/department/`+depId+`/employee/`+empId+`/add`, body);
  }

  removeDepFromEmp(depId:number, empId:number): Observable<any>{
    return this.http.delete(`http://localhost:8080/department/`+depId+`/employee/`+empId+`/remove`);
  }

  setOrgToEmp(orgId:number, empId:number, emp: Emp): Observable<any>{
    const body: Emp = {
      secondName:emp.secondName,
      firstName:emp.firstName,
      surname:emp.surname,
      phoneNumber:emp.phoneNumber,
      birthDate:emp.birthDate,
      email:emp.email,
      gender:emp.gender,
      genderS:emp.genderS,
      department:{} as  DepEmp,
      organization:{} as OrgEmp
        }
    return this.http.post(`http://localhost:8080/organization/`+orgId+`/employees/`+empId+`/add`, body);
  }

  removeOrgFromEmp(orgId:number, empId:number): Observable<any>{
    return this.http.delete(`http://localhost:8080/organization/`+orgId+`/employees/`+empId+`/remove`);
  }

  /*Должность*/
  getPoss(): Observable<any>{
    return this.http.get("http://localhost:8080/position");
  }

  setNewPos(pos: Pos): Observable<any>{
    const body: Pos = {
        namePosition:pos.namePosition,
        salary:pos.salary,
        countEmployees:pos.countEmployees,
        empls:[]
        }
    return this.http.post("http://localhost:8080/position", body);
  }

  updatePos(id: number, pos: Pos): Observable<any>{
    const body: Pos = {
        namePosition:pos.namePosition,
        salary:pos.salary,
        countEmployees:pos.countEmployees,
        empls:[] as EmpPos[]
        }
    return this.http.put(`http://localhost:8080/position/`+id, body);
  }

  removePos(id: number){
    return this.http.delete(`http://localhost:8080/position/`+id);
  }

  findPosByName(name:string): Observable<any>{
    return this.http.get(`http://localhost:8080/position/` + name + `/getByName`);
  }

  findPosById(id:number): Observable<any>{
    return this.http.get(`http://localhost:8080/position/` + id);
  }

  setEmpToPos(empId:number, id:number, pos: Pos){
    const body: Pos = {
        namePosition:pos.namePosition,
        salary:pos.salary,
        countEmployees:pos.countEmployees,
        empls:[] as EmpPos[]
        }
        return this.http.post(`http://localhost:8080/position/`+id+`/employees/`+empId+`/add`, body);
  }

  removeEmpFromPos(empId:number, id:number){
    return this.http.delete(`http://localhost:8080/position/`+id+`/employees/`+empId+`/remove`);
  }
}
