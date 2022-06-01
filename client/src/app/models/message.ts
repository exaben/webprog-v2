export interface MessageType {
    id?: number,
    senderId: number,
    receivedId: number,
    message: string,
    sendDate: Date
}