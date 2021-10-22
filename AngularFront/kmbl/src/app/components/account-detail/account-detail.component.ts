import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { TokenStorageService } from 'src/app/token-storage.service';
import { KmobileService } from 'src/app/kmobile.service';
import { Device } from 'src/app/model/device';
import { Plan } from 'src/app/model/plan';
import Account from 'src/app/model/account';


@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  account!: Account;
  planlist!: Plan[];
  phone!: Device;
  sum = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private service: KmobileService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    const userJson = localStorage.getItem('user'); 
    this.account = userJson !== null ? JSON.parse(userJson) : new Account(); 

    this.service.fetchAccountById(this.account.accountId).subscribe( (data)=> {
      this.account = data;
      this.planlist = this.account.plan;

      for (let onePlan of this.planlist) {
        if (onePlan.planType === "a") {
          this.sum += (25 * onePlan.devices.length);
        }
        if (onePlan.planType === "b") {
          this.sum += (35 * onePlan.devices.length);
        }
        if (onePlan.planType === "c") {
          this.sum += (45 * onePlan.devices.length);
        }
      }
      
    },
    err => {

    });
  }

  showAppPlan() : void {}

}