import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/models/IUser';
import { CognitoService } from 'src/app/services/cognito.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loading: boolean;
  user: IUser;
  constructor(private router: Router, private cognitoService: CognitoService) { 
    this.user = {} as IUser;  
    this.loading = false;
  }

  ngOnInit(): void {
  }

  login(){
    this.loading = true;
    this.cognitoService.signIn(this.user)
      .then((res)=>{
          console.log(res);
      }).catch(()=>{
        this.loading=false;
      })
  }
}
