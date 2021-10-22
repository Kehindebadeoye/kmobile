import { Component, OnInit,Input } from '@angular/core';
import { TokenStorageService } from 'src/app/token-storage.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Plan } from 'src/app/model/plan';
import { Device } from 'src/app/model/device';
import { KmobileService } from 'src/app/kmobile.service';

@Component({
  selector: 'app-plan-detail',
  templateUrl: './plan-detail.component.html',
  styleUrls: ['./plan-detail.component.css']
})
export class PlanDetailComponent implements OnInit {
  @Input() onePlan!: Plan;
  onePhone!: Device;
  addPhoneBox!:boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private service: KmobileService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.addPhoneBox = false;
  }
  showAddDevice(): void { 
    this.addPhoneBox = !this.addPhoneBox;
  }


  editPhone(): void {

  }


  deletePlan(): void {
    this.service.deletePlan(this.onePlan.planId).subscribe((data) => {
      this.router.navigate(['/home']).then(() => {
        parent.location.reload();
      });
    },
      err => {
      });
  }

  deletePhone(phone_id: number): void {
    this.service.deleteDevice(phone_id).subscribe((data)=> {
      this.router.navigate(['/home']).then(() => {
        parent.location.reload();
      });

    },
    err => {

    });

  }


  reloadPage(): void {
    window.location.reload();
  }

}