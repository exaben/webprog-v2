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

  

  constructor(private router: Router, private messageService: MessagesService, private matchingService: MatchingService) { 
  }

  ngOnInit(): void {
  }

  

  

}
