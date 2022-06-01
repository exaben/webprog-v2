import { InterestType } from "./interest";
export interface ProfileType {
    id: number,
    userID: number,
    firstname?: string,
    lastname?: string,
    description?: string,
    birthDate?: Date,
    age?: number,
    interests?: Array<InterestType>;
    imagePath?:	string
}