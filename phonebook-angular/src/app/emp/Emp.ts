export class Emp{
  secondName:string = "";
  firstName:string = "";
  surname:string = "";
  phoneNumber:string = "";
  birthDate:string = "";
  email:string = "";
  genderS:string = "";
  gender:number = 0;
  department:DepEmp = new DepEmp();
  organization:OrgEmp = new OrgEmp();
  }

export class DepEmp{
    depName:string = "";
}

export class OrgEmp{
    nameOrg:string = "";
}
