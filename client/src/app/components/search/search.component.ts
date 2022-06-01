import { UserType } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Component, EventEmitter, OnInit, Output, } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from 'src/app/services/profile.service';
import { ProfileType } from 'src/app/models/profile';
import { formatDate } from '@angular/common';
import { MatchingService } from 'src/app/services/matching.service';
import { MatchedType } from 'src/app/models/matched';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  

  user: UserType = JSON.parse(localStorage.getItem("user")!);


  currentDate: Date = new Date();

  constructor(private profileService: ProfileService,private userService: UserService ,private router: Router, private matchingService: MatchingService ) {
    
   }

  ngOnInit(): void {
    
  }

  
}
