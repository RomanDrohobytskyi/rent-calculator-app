export class UtilityBill {
  id: number;
  utilityType: UtilityBillType;
  meterState: number;
  consumption: number;
  cost: number;
}

export enum UtilityBillType {
  GAS = 'GAS',
  ELECTRICITY = 'ELECTRICITY',
  WATER = 'WATER'
}
