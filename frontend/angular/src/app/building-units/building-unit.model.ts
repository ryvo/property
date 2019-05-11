export enum BuildingUnitType {
  RESIDENTIAL,
  COMMERCIAL
}

export class BuildingUnit {
  id: number;
  name: string;
  type: BuildingUnitType;
  storey: number;
  area: number;
}
