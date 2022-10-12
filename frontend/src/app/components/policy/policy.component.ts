import { Component, Input, OnInit } from '@angular/core';
import { Policy } from 'src/app/models/Policy';

@Component({
  selector: 'app-policy',
  templateUrl: './policy.component.html',
  styleUrls: ['./policy.component.css']
})
export class PolicyComponent implements OnInit {
  @Input() policyData!: Policy;

  constructor() { }

  ngOnInit(): void {
  }
  
}
