import { Component, ElementRef, OnInit } from '@angular/core';
import { MatchedType } from 'src/app/models/matched';
import { MessageType } from 'src/app/models/message';
import { ProfileType } from 'src/app/models/profile';
import { UserType } from 'src/app/models/user';
import { MatchingService } from 'src/app/services/matching.service';
import { MessagesService } from 'src/app/services/messages.service';
import { ProfileService } from 'src/app/services/profile.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  profiles: ProfileType[] = [];
  contactProfile!: ProfileType;

  user: UserType = JSON.parse(localStorage.getItem("user")!);

  currentDate: Date = new Date();

  partnerName!: string;

  messageList: MessageType[] = [];

  userProfile!: ProfileType;

  constructor(private matchingService: MatchingService, private profileService: ProfileService, private messageService: MessagesService, private elementRef: ElementRef) {
    this.getMatched();

  }

  ngOnInit(): void {
    if (this.messageService.contactId !== undefined) {
      this.setPartner(this.messageService.contactId);
      this.profileService.getById(this.messageService.contactId).subscribe(
        response => {
          this.partnerName = response.firstname + " " + response.lastname;
        }
      )
    }
  }

  setPartner(id: number) {
    this.profileService.getById(id).subscribe(
      response => {
        this.contactProfile = response;
        console.log("test", this.contactProfile);
        this.partnerName = response.firstname + " " + response.lastname;
        this.getMessage(this.user.id, this.contactProfile.userID);
      }
    )
  }

  getProfiles(matchedList: MatchedType[]) {
    this.profileService.getAll().subscribe(
      response => {
        matchedList.forEach((match: MatchedType) => {
          let findedProfile = response.find(p => p.userID == match.likedUserId);

          if (findedProfile !== undefined) {
            this.profiles.push(findedProfile);
          }
        })
        
        this.userProfile = response.find(p => p.userID == this.user.id)!
      }
    )
  }

  getMatched() {
    this.matchingService.getById(this.user.id).subscribe(
      response => {
        this.getProfiles(response);
      }
    )
  }

  //elküldi az üzentet
  sendMessage() {
    let messageInput: HTMLInputElement = this.elementRef.nativeElement.querySelector("#message");

    if (messageInput.value.length > 0) {
      let message = { senderId: this.user.id, receivedId: this.contactProfile.userID, message: messageInput.value, sendDate: this.currentDate };
      this.messageService.post(message).subscribe(
        response => {
          messageInput.value = "";
          this.getMessage(this.user.id, this.contactProfile.userID);
        }
      )
    }
  }

  //lekéri a beszélgetés üzenetetit
  getMessage(senderId: number, receivedId: number) {
    this.messageService.getById(senderId, receivedId).subscribe(
      response => {
        this.messageList = response;
        console.log(response);
      }
    )
  }

  scroll() {
    let box: HTMLDivElement = this.elementRef.nativeElement.querySelector("#box");
    box.scrollTop = box.scrollHeight
  }
}
