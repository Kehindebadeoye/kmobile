import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/token-storage.service';
import { Emitters } from '../Emitters';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  roles: string[] = [];
  isLoggedIn = false;
  auththticated = false;
  username?: string;

  constructor(
    private tokenStorageService: TokenStorageService
  ) { }

  ngOnInit(): void {

    this.isLoggedIn =!!this.tokenStorageService.getToken();

    Emitters.authEmitter.subscribe((auth: boolean)=> {
      this.auththticated = auth;
    });


    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.username = user.username;
    }
  }

  logout(): void {
    
    this.tokenStorageService.signOut();
    this.auththticated = false;
    this.reloadPage();
  }

    reloadPage(): void {
    window.location.reload();
  }

}
