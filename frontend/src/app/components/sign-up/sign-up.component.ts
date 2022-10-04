import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/models/IUser';
import { CognitoService } from 'src/app/services/cognito.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  loading:boolean;
  isConfirm:boolean;
  user: IUser;
  constructor(private router: Router, private cognitoService: CognitoService) { 
    this.loading = false;
    this.isConfirm = false;
    this.user = {} as IUser;  
  }

  ngOnInit(): void {
  }

  public signUp(): void{
    
    this.loading = true;
    this.cognitoService.signUp(this.user)
      .then((res)=>{
        this.loading = false;
        this.isConfirm = true;
        console.log(res);
        console.log("success");
      }).catch((res)=>{
        this.loading = false;
        console.log("fail")
        console.log(res);
      })
  }

  public confirmSignUp():void{
    this.loading = true;
    this.cognitoService.confirmSignUp(this.user)
      .then(()=>{
        this.router.navigate(['/login']);
      }).catch(()=>{
        this.loading = false;
      })
  }

}
