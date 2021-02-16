import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {APP_BASE_HREF} from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrgComponent } from './org/org.component';
import { DepComponent } from './dep/dep.component';
import { FilComponent } from './fil/fil.component';
import { EmpComponent } from './emp/emp.component';
import { PosComponent } from './pos/pos.component';

@NgModule({
  declarations: [
    AppComponent,
    OrgComponent,
    DepComponent,
    FilComponent,
    EmpComponent,
    PosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
   providers: [{provide: APP_BASE_HREF, useValue: '/phonebook'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
