import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { MatchedType } from 'src/app/models/matched';
import { ProfileType } from 'src/app/models/profile';
import { UserType } from 'src/app/models/user';
import { MatchingService } from 'src/app/services/matching.service';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-people-card',
  templateUrl: './people-card.component.html',
  styleUrls: ['./people-card.component.css']
})
export class PeopleCardComponent implements OnInit {

  @Input()
  profile!: ProfileType;


  @Input()
  user!: UserType;

  @Input()
  matchedList: MatchedType[] = [];

  constructor(private router: Router, private messageService: MessagesService, private matchingService: MatchingService) { 
  }

  ngOnInit(): void {
  }

  //beállítja a messageService-ben a kontakt személyt és átnavigál a message oldalra
  sendMessage(){
    this.messageService.contactId = this.profile.userID;
    this.router.navigate(['/message']);
  }

  //ellenőrzi, hogy a felhasználó bekedvelt-e valakit
  checkMatch(){
    if(this.matchedList.find(m => m.likedUserId == this.profile.userID)!= undefined) return true;
    return false;
  }

  

}
