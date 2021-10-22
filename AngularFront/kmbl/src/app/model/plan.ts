import { Device } from "./device";

export class Plan {
    planId!: number;
    planType!: string;
    devices! : Device[];
    planPrice! : number;

}
