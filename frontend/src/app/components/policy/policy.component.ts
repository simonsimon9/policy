import { Component, OnInit } from '@angular/core';
import { PolicyService } from 'src/app/services/policy.service';

@Component({
  selector: 'app-policy',
  templateUrl: './policy.component.html',
  styleUrls: ['./policy.component.css']
})
export class PolicyComponent implements OnInit {


  constructor( private policyService : PolicyService) { }

  ngOnInit(): void {
  }
  getAll() {
    console.log("get all");
    this.policyService.getAllPolicies().subscribe((response)=>{
      console.log(response);
    });
  }
}
