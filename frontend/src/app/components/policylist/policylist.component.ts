import { Component, Input, OnInit } from '@angular/core';
import { Policy } from 'src/app/models/Policy';
import { PolicyService } from 'src/app/services/policy.service';

@Component({
  selector: 'app-policylist',
  templateUrl: './policylist.component.html',
  styleUrls: ['./policylist.component.css']
})
export class PolicylistComponent implements OnInit {
  @Input() policies!: Policy[];
  constructor(private policyService : PolicyService) { }

  ngOnInit(): void {
    // console.log("get all");
    // this.policyService.getAllPolicies().subscribe({
    //   next: (response) =>{this.policies = response.body;},
    //   error: (error) =>{console.log(error)}
    // });
  }
  getAll() {
    
  }

}
