import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetallComponent } from './components/getall/getall.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterPolicyComponent } from './components/register-policy/register-policy.component';
import { SearchComponent } from './components/search/search.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'register', component: SignUpComponent},
  {path: 'login', component: LoginComponent},
  {path: 'getall', component: GetallComponent},
  {path: 'register-policy', component: RegisterPolicyComponent},
  {path: 'search', component: SearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
