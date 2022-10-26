import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Policy } from '../models/Policy';

@Injectable({
  providedIn: 'root'
})

export class PolicyService {
   httpOptions = {
    headers: new HttpHeaders({ 
      'Content-Type': 'application/json',
    })
  };

  url : string = environment.awsApi;
  constructor(private http:HttpClient) { }

  getAllPolicies() : Observable<any>{
    return this.http.get(`${this.url}/policy/getall`,this.httpOptions);
  }

  registerPolicy(policy: Policy) : Observable<any>{
    return this.http.post(`${this.url}/policy/register`, policy);
  }

}
