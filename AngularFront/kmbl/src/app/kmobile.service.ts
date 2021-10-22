import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Account from './model/account';
import { Plan } from './model/plan';
import { Device } from './model/device';


const accountUrl = 'http://localhost:8085/accounts';
const getUserUrl = 'http://localhost:8085/accounts/login';
const deviceUrl = 'http://localhost:8085/devices';
const oneDeviceUrl = 'http://localhost:8085/devices/device';
const planUrl = 'http://localhost:8085/plans/plan';
const putPlanToAcct_url_1 = 'http://localhost:8085/plans/';
const putPlanToAcct_url_2 = '/addPlan/';
const putPhoneToPlan_url_1 = "http://localhost:8085/devices/";
const putPhoneToPlan_url_2 = "/addDevice/";

const generateNewNumber_url = "http://localhost:8085/devices/device/generateNumber"


@Injectable({
  providedIn: 'root'
})
export class KmobileService {

  temp_url = "";

  constructor(private httpClient: HttpClient) { }


  //...................................................................................AccountService

  findAllAccount(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(accountUrl);
  }
  saveAccount(account: Account): Observable<Account> {
    return this.httpClient.post<Account>(accountUrl, account);
  }
  logInAccount(account: Account): Observable<Account> {
    return this.httpClient.post<Account>(getUserUrl, account);
  }
  fetchAccountById(aid: number): Observable<Account> {
    return this.httpClient.get<Account>(accountUrl + '/' + aid);
  }

  //...................................................................................PlanService

  savePlan(plan: Plan): Observable<Plan> {
    return this.httpClient.post<Plan>(planUrl, plan);
  }

  connectPlanToAcct(planId: number, acc_id: number): void {
    this.temp_url = putPlanToAcct_url_1 + planId + putPlanToAcct_url_2 + acc_id;
    this.httpClient.put(this.temp_url, null).subscribe();
  }

  deletePlan(planId: number): Observable<any> {
    return this.httpClient.delete(planUrl + "/" + planId);
  }
  //...................................................................................DeviceService

  findAllDevices(): Observable<Device[]> {
    return this.httpClient.get<Device[]>(deviceUrl);
  }

  saveDevice(device: Device): Observable<Device> {
    return this.httpClient.post<Device>(oneDeviceUrl, device);
  }

  deleteDevice(deviceId: number): Observable<any> {
    return this.httpClient.delete(oneDeviceUrl + "/" + deviceId);
  }

  connectPhoneToPlan(phone_id: number, plan_id: number): void {
    this.temp_url = putPhoneToPlan_url_1 + phone_id + putPhoneToPlan_url_2 + plan_id;
    //console.log(this.temp_url);
    this.httpClient.put(this.temp_url, null).subscribe();
  }

  generateNewNumber(): Observable<any> {
    return this.httpClient.get<string>(generateNewNumber_url);
  }

  
}
