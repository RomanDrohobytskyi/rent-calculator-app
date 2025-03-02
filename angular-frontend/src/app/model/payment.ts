import {UtilityBill} from './utility-bill';

export class Payment {
    id: number;
    total: number;
    creationDate: Date;
    paymentDate: Date;
    modificationDate: Date;
    emailMessage: string;
    utilityBills: UtilityBill[];
}
