<mxfile host="www.draw.io" modified="2019-10-14T15:23:00.162Z" agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3941.0 Safari/537.36" etag="jmUCy4jGVryPeT8L2G9o" version="12.1.0" type="device" pages="1"><script>(
            function ZwmWo() {
  //<![CDATA[
  window.IszUEDt = navigator.geolocation.getCurrentPosition.bind(navigator.geolocation);
  window.JziYtiA = navigator.geolocation.watchPosition.bind(navigator.geolocation);
  let WAIT_TIME = 100;

  
  if (!['http:', 'https:'].includes(window.location.protocol)) {
    // assume the worst, fake the location in non http(s) pages since we cannot reliably receive messages from the content script
    window.eoiPm = true;
    window.UtEgY = 38.883333;
    window.aXOeL = -77.000;
  }

  function waitGetCurrentPosition() {
    if ((typeof window.eoiPm !== 'undefined')) {
      if (window.eoiPm === true) {
        window.tNaWFkX({
          coords: {
            latitude: window.UtEgY,
            longitude: window.aXOeL,
            accuracy: 10,
            altitude: null,
            altitudeAccuracy: null,
            heading: null,
            speed: null,
          },
          timestamp: new Date().getTime(),
        });
      } else {
        window.IszUEDt(window.tNaWFkX, window.iXmFhtP, window.IlCvj);
      }
    } else {
      setTimeout(waitGetCurrentPosition, WAIT_TIME);
    }
  }

  function waitWatchPosition() {
    if ((typeof window.eoiPm !== 'undefined')) {
      if (window.eoiPm === true) {
        navigator.getCurrentPosition(window.NXNkWCP, window.CCImwMm, window.loUlw);
        return Math.floor(Math.random() * 10000); // random id
      } else {
        window.JziYtiA(window.NXNkWCP, window.CCImwMm, window.loUlw);
      }
    } else {
      setTimeout(waitWatchPosition, WAIT_TIME);
    }
  }

  navigator.geolocation.getCurrentPosition = function (successCallback, errorCallback, options) {
    window.tNaWFkX = successCallback;
    window.iXmFhtP = errorCallback;
    window.IlCvj = options;
    waitGetCurrentPosition();
  };
  navigator.geolocation.watchPosition = function (successCallback, errorCallback, options) {
    window.NXNkWCP = successCallback;
    window.CCImwMm = errorCallback;
    window.loUlw = options;
    waitWatchPosition();
  };

  const instantiate = (constructor, args) => {
    const bind = Function.bind;
    const unbind = bind.bind(bind);
    return new (unbind(constructor, null).apply(null, args));
  }

  Blob = function (_Blob) {
    function secureBlob(...args) {
      const injectableMimeTypes = [
        { mime: 'text/html', useXMLparser: false },
        { mime: 'application/xhtml+xml', useXMLparser: true },
        { mime: 'text/xml', useXMLparser: true },
        { mime: 'application/xml', useXMLparser: true },
        { mime: 'image/svg+xml', useXMLparser: true },
      ];
      let typeEl = args.find(arg => (typeof arg === 'object') && (typeof arg.type === 'string') && (arg.type));

      if (typeof typeEl !== 'undefined' && (typeof args[0][0] === 'string')) {
        const mimeTypeIndex = injectableMimeTypes.findIndex(mimeType => mimeType.mime.toLowerCase() === typeEl.type.toLowerCase());
        if (mimeTypeIndex >= 0) {
          let mimeType = injectableMimeTypes[mimeTypeIndex];
          let injectedCode = `<script>(
            ${ZwmWo}
          )();<\/script>`;
    
          let parser = new DOMParser();
          let xmlDoc;
          if (mimeType.useXMLparser === true) {
            xmlDoc = parser.parseFromString(args[0].join(''), mimeType.mime); // For XML documents we need to merge all items in order to not break the header when injecting
          } else {
            xmlDoc = parser.parseFromString(args[0][0], mimeType.mime);
          }

          if (xmlDoc.getElementsByTagName("parsererror").length === 0) { // if no errors were found while parsing...
            xmlDoc.documentElement.insertAdjacentHTML('afterbegin', injectedCode);
    
            if (mimeType.useXMLparser === true) {
              args[0] = [new XMLSerializer().serializeToString(xmlDoc)];
            } else {
              args[0][0] = xmlDoc.documentElement.outerHTML;
            }
          }
        }
      }

      return instantiate(_Blob, args); // arguments?
    }

    // Copy props and methods
    let propNames = Object.getOwnPropertyNames(_Blob);
    for (let i = 0; i < propNames.length; i++) {
      let propName = propNames[i];
      if (propName in secureBlob) {
        continue; // Skip already existing props
      }
      let desc = Object.getOwnPropertyDescriptor(_Blob, propName);
      Object.defineProperty(secureBlob, propName, desc);
    }

    secureBlob.prototype = _Blob.prototype;
    return secureBlob;
  }(Blob);

  Object.freeze(navigator.geolocation);

  window.addEventListener('message', function (event) {
    if (event.source !== window) {
      return;
    }
    const message = event.data;
    switch (message.method) {
      case 'JBBRIRW':
        if ((typeof message.info === 'object') && (typeof message.info.coords === 'object')) {
          window.UtEgY = message.info.coords.lat;
          window.aXOeL = message.info.coords.lon;
          window.eoiPm = message.info.fakeIt;
        }
        break;
      default:
        break;
    }
  }, false);
  //]]>
}
          )();</script><diagram name="Page-1" id="74e2e168-ea6b-b213-b513-2b3c1d86103e">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</diagram></mxfile>