export class Category {
  constructor(public id: number,
              public name:string,
              public hasSubCategory: boolean,
              public parentId: number){ }
}

export class Product {
  constructor(public id: number,
              public name: string,
              public shortDesc: string,
              public icon: string,
              public longDesc: string,
              public picture: string,
              public descUrl: string,
              public type: string,
              public typeName: string,
              public vendor: string,
              public vendorName: string,
              public category: string,
              public pictures:Array<any>,
              public tags:Array<any>,
              public categoryName: string
              ){ }
}
