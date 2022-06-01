import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatchedType } from 'src/app/models/matched';
import { ProfileType } from 'src/app/models/profile';
import { UserType } from 'src/app/models/user';
import { MatchingService } from 'src/app/services/matching.service';

@Component({
  selector: 'app-people-modal',
  templateUrl: './people-modal.component.html',
  styleUrls: ['./people-modal.component.css']
})
export class PeopleModalComponent implements OnInit {

 

  constructor(private matchingService: MatchingService) {}

  ngOnInit(): void { 
    

  }



  onSubmit(): void {
  
  }

 
}
