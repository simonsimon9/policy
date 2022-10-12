import { Component, OnInit } from '@angular/core';
import { CognitoService } from 'src/app/services/cognito.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userName!: String;
  constructor(private cognitoservice : CognitoService) {

   }

  async ngOnInit(): Promise<void> {
    let response = await this.cognitoservice.getUser();
    try{
      this.userName = response.username;
      console.log(this.userName);
    }catch(error){
      console.log(error);
    }
    
    document.getElementById('menu-btn')!.addEventListener('click', () => {
      document.getElementById('menu-btn')!.classList.toggle('open')
      document.getElementById('menu')!.classList.toggle('flex')
      document.getElementById('menu')!.classList.toggle('hidden')
    })
  }
  
  async test(){
    let response = await this.cognitoservice.getUser();
    console.log(response.username);
  }

  async logout(){
    await this.cognitoservice.signOut();
    this.userName = "";
    
    
  }

}
