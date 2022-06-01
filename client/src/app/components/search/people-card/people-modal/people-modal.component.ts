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

  @Input()
  profile!: ProfileType;

  @Input()
  user!: UserType;

  @Output()
  actionEmit = new EventEmitter<boolean>();

  userMatchedList: MatchedType[] = [];

  matched!: MatchedType;

  now: Date = new Date();

  constructor(private matchingService: MatchingService) {}

  ngOnInit(): void { 
    this.getUserMatched();

  }

//Lekéri az összes matchet amit a felhasználó készített
  getUserMatched(): void {
    this.matchingService.getById(this.user.id).subscribe(
      response => {
        this.userMatchedList = response;
        console.log(this.userMatchedList);
      }
    )
  }

  //megkeresi hogy van e már az adott két felhasználó között match
  findMatch(){
    return this.userMatchedList.find(m => m.likedUserId === this.profile.userID);
  }

  onSubmit(): void {
    let findedMatched = this.findMatch();

    console.log(findedMatched);
    if (findedMatched !== undefined) {
      this.deleteMatch(findedMatched.id!);
    } else {
      this.createMatch();
    }
  }

  //Matchet hozz létre
  createMatch() {
    this.matched = {userId: this.user.id, likedUserId: this.profile.userID, likedDate: this.now };
    this.matchingService.post(this.matched).subscribe(
      response => {
        this.actionEmit.emit(true);
        console.log(response);
        this.getUserMatched();
      }
    )
  }

  //törli a már meglévő matchet
  deleteMatch(id: number) {
    this.matchingService.delete(id).subscribe(
      response => {
        this.actionEmit.emit(true);
        this.getUserMatched();
      }
    );

  }

}
