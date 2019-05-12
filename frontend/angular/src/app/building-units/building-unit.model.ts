export enum BuildingUnitType {
  RESIDENTIAL = 'buildings.units.type.residential',
  COMMERCIAL = 'buildings.units.type.commercial'
}

export class BuildingUnit {
  id: number;
  name: string;
  type: BuildingUnitType;
  storey: number;
  area: number;
}
