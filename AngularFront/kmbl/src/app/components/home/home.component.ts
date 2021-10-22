import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  email!: string;
  isLoggedIn = false;
  roles: string[] = [];
  jsonString!: string;

  constructor(
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {

    if (!localStorage.getItem('user')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
    } else {
      localStorage.removeItem('foo') 
    }

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }

    

    
       
  }

  reloadPage(): void {
    window.location.reload();
  }

}
