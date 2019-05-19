export enum TenantType {
  NATURAL_PERSON = 'NATURAL_PERSON',
  LEGAL_PERSON = 'LEGAL_PERSON'
}

export class Tenant {
  id: number;
  type: TenantType;
  firstName: string;
  lastName: string;
  companyName: string;
  houseNumber: string;
  registryNumber: string;
  streetName: string;
  townName: string;
  postalCode: string;
  countryName: string;
  telephone1: string;
  telephone2: string;
  email: string;
}
