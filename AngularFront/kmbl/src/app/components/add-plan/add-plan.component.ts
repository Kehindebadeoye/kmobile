import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { KmobileService } from 'src/app/kmobile.service';
import Account from 'src/app/model/account';
import { Plan } from 'src/app/model/plan';
import { TokenStorageService } from 'src/app/token-storage.service';

@Component({
  selector: 'app-add-plan',
  templateUrl: './add-plan.component.html',
  styleUrls: ['./add-plan.component.css']
})
export class AddPlanComponent implements OnInit {

  newPlan!: Plan;
  account!: Account;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private service: KmobileService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.newPlan = new Plan();

  }

  addPlanA(): void {
    this.newPlan.planType = "a";
    this.addPlan();
  }

  addPlanB(): void {
    this.newPlan.planType = "b";
    this.addPlan();
  }

  addPlanC(): void {
    this.newPlan.planType = "c";
    this.addPlan();
  }

  addPlan(): void {
    this.service.savePlan(this.newPlan).subscribe((data) => {
      this.newPlan = data;

      const userJson = localStorage.getItem('user');
      this.account = userJson !== null ? JSON.parse(userJson) : new Account();

      console.log("put plan to account");
      this.service.connectPlanToAcct(this.newPlan.planId, this.account.accountId);
      this.router.navigate(['/home'])
    },
      err => {
      });
  }

}
