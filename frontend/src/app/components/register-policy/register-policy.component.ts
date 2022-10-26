import { Component, OnInit } from '@angular/core';
import { Policy } from 'src/app/models/Policy';
import { PolicyService } from 'src/app/services/policy.service';

@Component({
  selector: 'app-register-policy',
  templateUrl: './register-policy.component.html',
  styleUrls: ['./register-policy.component.css']
})
export class RegisterPolicyComponent implements OnInit {
  policy: Policy;
  constructor(private policyService: PolicyService) { 
    this.policy = {} as Policy;
  }

  ngOnInit(): void {
  }

  registerPolicy(){
    this.policyService.registerPolicy(this.policy).subscribe(
      (result)=>{
        console.log("added");
      }
    )
  }

}
