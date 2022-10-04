import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import Amplify, { Auth } from 'aws-amplify';
import { environment } from 'src/environments/environment';
import { IUser } from '../models/IUser';


@Injectable({
  providedIn: 'root'
})
export class CognitoService {
  private authenticationSubject: BehaviorSubject<any>;

  constructor() {
    Amplify.configure({Auth: environment.cognito});
    this.authenticationSubject = new BehaviorSubject<boolean>(false);
   }

   public signUp(user:IUser): Promise<any>{
    return Auth.signUp({
      username:user.username,
      password: user.password,
      attributes: {
        email: user.email
      }
    })
   }

   public confirmSignUp(user:IUser): Promise<any> {
    return Auth.confirmSignUp(user.email, user.password)
      .then(()=>{
        this.authenticationSubject.next(true);
      })
   }
   public signIn(user: IUser): Promise<any> {
    return Auth.signIn(user.username, user.password)
    .then(() => {
      this.authenticationSubject.next(true);
    });
  }
   public signOut(): Promise<any> {
    return Auth.signOut()
      .then(()=>{
        this.authenticationSubject.next(false);
      })
   }

   public getUser():Promise<any>{
    return Auth.currentUserInfo();
   }

   public updateUser(user:IUser): Promise<any> {
    return Auth.currentUserPoolUser()
      .then((cognitoUser:any)=>{
        return Auth.updateUserAttributes(cognitoUser,user);
      })
   }

   public isAuthenticated(): Promise<boolean> {
    if (this.authenticationSubject.value) {
      return Promise.resolve(true);
    } else {
      return this.getUser()
      .then((user: any) => {
        if (user) {
          return true;
        } else {
          return false;
        }
      }).catch(() => {
        return false;
      });
    }
  }
}
