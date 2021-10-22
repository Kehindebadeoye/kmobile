import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { KmobileService } from 'src/app/kmobile.service';
import { TokenStorageService } from 'src/app/token-storage.service';
import Account from 'src/app/model/account';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  isLoggedIn = false;
  accountForm!: FormGroup;
  account!: Account;
  roles: string[] = [];
  errorMessage = "";

  constructor(

    private route: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private service: KmobileService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.accountForm = this.formBuilder.group({
      email: new FormControl("", [
        Validators.email,
        Validators.required
      ]),
      password: new FormControl("", [
        Validators.minLength(2)
      ]),
    })
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  register(): void {
    console.log(this.accountForm.value);
    this.service.saveAccount(this.accountForm.value).subscribe(data => {
      this.account = data;
      localStorage.setItem("user", JSON.stringify(this.account));
      this.tokenStorage.saveToken("token");
      this.tokenStorage.saveUser(this.account);
      this.isLoggedIn = true;

      this.router.navigate(['/home']).then(() => {
        parent.location.reload();
      });

    }, err => {
      this.errorMessage = "account or password not correct"
    });
  }


  reloadPage(): void {
    window.location.reload();
  }


}
