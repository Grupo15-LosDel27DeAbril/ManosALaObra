import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DataService } from '../data.service';
import { SocialUser } from 'angularx-social-login';
import { AuthService } from 'angularx-social-login';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  usuarioName: String = "";
  private user: SocialUser;
  private loggedIn: boolean;


  constructor(public appcomp: AppComponent,private route: Router, public data: DataService,private authService: AuthService) { }

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
               this.user = user;
               this.loggedIn = (user != null);
               if(this.loggedIn == false){
                this.route.navigateByUrl('/');
             }
          });
  }

  signOut(): void{
    this.route.navigateByUrl('login');
    this.authService.signOut();
  }

}