import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { UserType } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ProfileType } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { sha512 } from 'js-sha512';
import { InterestType } from 'src/app/models/interest';
import { PasswordType } from 'src/app/models/password';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  

  user: UserType = JSON.parse(localStorage.getItem("user")!);

  constructor(private userService: UserService, private profileService: ProfileService) {
    
  }

 

  ngOnInit(): void {
   
  }

}