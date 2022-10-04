import { Component, OnInit } from '@angular/core';
import { PolicyService } from 'src/app/services/policy.service';

@Component({
  selector: 'app-policylist',
  templateUrl: './policylist.component.html',
  styleUrls: ['./policylist.component.css']
})
export class PolicylistComponent implements OnInit {

  constructor(private policyService : PolicyService) { }

  ngOnInit(): void {
  }
  getAll() {
    console.log("get all");
    this.policyService.getAllPolicies().subscribe({
      next: (response) =>{console.log(response)},
      error: (error) =>{console.log(error)}
    });
  }

}
