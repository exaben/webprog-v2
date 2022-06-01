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

  profile!: ProfileType[];

  user: UserType = JSON.parse(localStorage.getItem("user")!);

  matchedList: MatchedType[] = [];

  currentDate: Date = new Date();

  constructor(private profileService: ProfileService,private userService: UserService ,private router: Router, private matchingService: MatchingService ) {
    this.getProfiles()
   }

  ngOnInit(): void {
    
  }


  //Lekéri az összes regisztrált felhasználó profilját
  getProfiles() {
   this.profileService.getAll().subscribe(
      response => {
        this.profile = response;
        //törli a bejelentkezett user-t a listából, így megjelenítésnél nem fog maradni egy üres tábla
        let index = this.profile.indexOf(this.profile.find(p => p.userID == this.user.id)!);
        this.profile.splice(index, 1);
      }
    )
  }

  //a card-ok kitöltéséhez userId-k alapján lekérésre kerülnek a profilok
  getProfileById(userId: number) : ProfileType {
    let prof = this.profile?.find(prof => prof.userID == userId  && prof.userID != this.user.id);
    return prof ? prof : {userID: 0, id: 0, firstname: "", lastname: "", description: "", age: 0, birthDate: this.currentDate, interests: [], imagePath: ""} ;
  }

}
