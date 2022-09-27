import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    
  constructor() {

   }

  ngOnInit(): void {
    document.getElementById('menu-btn')!.addEventListener('click', () => {
      document.getElementById('menu-btn')!.classList.toggle('open')
      document.getElementById('menu')!.classList.toggle('flex')
      document.getElementById('menu')!.classList.toggle('hidden')
    })
  }
  

}
