import { Plan } from "./plan";

export class Account {
    accountId!: number;
    email!: string;
    password!: string;
    plan! : Plan[];

}
export default Account;
