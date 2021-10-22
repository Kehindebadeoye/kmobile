import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import {Account} from 'src/app/model/account'
import { TokenStorageService } from 'src/app/token-storage.service';
import { KmobileService } from 'src/app/kmobile.service';
import { Box } from 'src/app/model/box';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  roles: string[] = [];
  accountForm!: FormGroup;
  errorMessage = '';
  account!: Account;


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

  login(): void {
    this.service.logInAccount(this.accountForm.value).subscribe(data => {
      this.account = data;
      localStorage.setItem("user", JSON.stringify(this.account));
      this.tokenStorage.saveToken("token");
      this.tokenStorage.saveUser(this.account);
      this.isLoggedIn = true;
      
      
      this.router.navigate(['/home']).then(()=>{
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