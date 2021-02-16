export class Dep{
    depName: string = "";
    plainFilialBranchDto: FilBranchDep = new FilBranchDep();
    plainOrgDto: OrgDep = new OrgDep();

  }

export class FilBranchDep{
    nameFilial:string = "";
}

export class OrgDep{
    nameOrg:string = "";
}
