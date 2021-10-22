import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/token-storage.service';
import { KmobileService } from 'src/app/kmobile.service';
import { Plan } from 'src/app/model/plan';
import { Device } from 'src/app/model/device';

@Component({
  selector: 'app-add-phone',
  templateUrl: './add-phone.component.html',
  styleUrls: ['./add-phone.component.css']
})
export class AddPhoneComponent implements OnInit {
  @Input() onePlan!: Plan;
  newPhoneToAdd!: FormGroup;
  phoneToAdd!: Device;
  phoneNumber!: string;
  phone_number_display!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private service: KmobileService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
  
    this.newPhoneToAdd = this.formBuilder.group({
      phoneNumber: new FormControl("", [
        Validators.required
      ]),
      phone_first_name: new FormControl("", [
        Validators.required
      ]),
      phone_last_name: new FormControl("", [
        Validators.required
      ]),
    });

    // let randomPhoneNumber = {
    //   'phone_area' : 123,
    //   'phone_number' : 1234567
    // }

    // this.newPhoneToAdd.patchValue(randomPhoneNumber);
  }

  getNumber(): void {
    this.service.generateNewNumber().subscribe((data: any) => {

      this.phoneNumber = data;
      let randomPhoneNumber = {
        'phoneNumber' : this.phoneNumber,
      }
      this.newPhoneToAdd.patchValue(randomPhoneNumber);
    },);
  }

  addPhone():void{
    console.log(this.newPhoneToAdd.value);
    this.service.saveDevice(this.newPhoneToAdd.value).subscribe((data)=> {
      this.phoneToAdd = data;
      this.service.connectPhoneToPlan(this.phoneToAdd.deviceId, this.onePlan.planId);

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

  getPhoneFormat(): void {
    this.phone_number_display = this.phoneNumber;

  }

}
