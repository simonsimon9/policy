import { Component, OnInit } from '@angular/core';
import { Policy } from 'src/app/models/Policy';
import { PolicyService } from 'src/app/services/policy.service';

@Component({
  selector: 'app-getall',
  templateUrl: './getall.component.html',
  styleUrls: ['./getall.component.css']
})
export class GetallComponent implements OnInit {
  policies: Policy[] = [];
  constructor(private policyService : PolicyService) { }

  ngOnInit(): void {
    console.log("get all");
    this.policyService.getAllPolicies().subscribe({
      next: (response) =>{this.policies = response.body;},
      error: (error) =>{console.log(error)}
    });
  }

}
