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
  profileForm!: FormGroup;
  passwordForm!: FormGroup;
  interests: InterestType[] = [];

  UserPassword!: PasswordType;


  @Input()
  profile!: ProfileType;

  user: UserType = JSON.parse(localStorage.getItem("user")!);

  constructor(private userService: UserService, private profileService: ProfileService) {
    this.getUserData();
  }

  profileDisable() {
    this.profileForm.disable();
  }
  profileEnable() {
    this.profileForm.enable();
  }
 
  //lekéri a felhasználó profil adatait
  getUserData() {
    this.profileService.getById(this.user.id).subscribe(
      response => {
        this.profile = response;
        this.interests = response.interests!;
        console.log(this.interests);
        this.initProfileForm();
      }
    )
  }

  onSubmitProfile() {
    this.profileService.put(this.profileForm.getRawValue()).subscribe(
      response => {
        console.log(response);
      },
    )
  }

  
  onSubmitPassword(): void {
    console.log(this.passwordForm.valid)
    console.log(this.passwordForm.value)
    if(!this.passwordForm.valid){
      this.UserPassword = this.passwordForm.getRawValue();

      this.UserPassword.opassword = sha512(this.UserPassword.opassword);
      this.UserPassword.password = sha512(this.UserPassword.password);
      this.UserPassword.pswrepeat = sha512(this.UserPassword.pswrepeat);
      
      console.log(this.user);
      this.userService.put(this.user.id, this.user).subscribe(
        response => {
          console.log("updated")
          },
      )
    }else{
      console.log("semmi")
    }
   
  }

  initPasswordForm(): void {
    this.passwordForm = new FormGroup({
      opassword: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.pattern('^(?=.[a-z])(?=.[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')]),
      pswrepeat: new FormControl(null, [Validators.required, this.samePasswordValidators.bind(this)]),
    })

  }

  initProfileForm(): void {
    this.profileForm = new FormGroup({
      firstName: new FormControl(this.profile ? this.profile.firstname : null, [Validators.required]),
      lastName: new FormControl(this.profile ? this.profile.lastname : null, [Validators.required]),
      birthDate: new FormControl(this.profile ? this.profile.birthDate : null, [Validators.required]),
      interest: new FormControl(this.profile ? this.profile.interests : null),
      description: new FormControl(this.profile ? this.profile.description : null),
    });
  }

  samePasswordValidators(control: FormControl): { [s: string]: boolean } | null {
    return this.passwordForm !== undefined && this.passwordForm.get('password')!.value === control.value
      ? null
      : { notSamePassword: true }
  }


  ngOnInit(): void {
    this.initPasswordForm();
    this.initProfileForm();
  }

}