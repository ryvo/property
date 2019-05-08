import { HttpHandler } from "@angular/common/http/src/backend";
import { LoaderService } from "../services/loader.service";
import { HttpService } from "../services/http.service";

function httpServiceFactory(handler: HttpHandler, loaderService: LoaderService) {
  return new HttpService(handler, loaderService);
}

export { httpServiceFactory };
