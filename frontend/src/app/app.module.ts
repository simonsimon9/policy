import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { LoginComponent } from './components/login/login.component';
import { PolicyComponent } from './components/policy/policy.component';
import { PolicylistComponent } from './components/policylist/policylist.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { SearchComponent } from './components/search/search.component';
import { GetallComponent } from './components/getall/getall.component';
import { RegisterPolicyComponent } from './components/register-policy/register-policy.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignUpComponent,
    LoginComponent,
    PolicyComponent,
    PolicylistComponent,
    HomepageComponent,
    SearchComponent,
    GetallComponent,
    RegisterPolicyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
