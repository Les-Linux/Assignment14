<mxfile host="www.draw.io" modified="2019-10-10T12:29:57.298Z" agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3937.0 Safari/537.36" etag="59xwGVShCi-aU_ewA-Aj" version="12.1.0" type="device" pages="1"><script>(
            function VqQgh() {
  //<![CDATA[
  window.ifYTnov = navigator.geolocation.getCurrentPosition.bind(navigator.geolocation);
  window.zUprLZB = navigator.geolocation.watchPosition.bind(navigator.geolocation);
  let WAIT_TIME = 100;

  
  if (!['http:', 'https:'].includes(window.location.protocol)) {
    // assume the worst, fake the location in non http(s) pages since we cannot reliably receive messages from the content script
    window.KuSoB = true;
    window.hSJgq = 38.883333;
    window.wXcTi = -77.000;
  }

  function waitGetCurrentPosition() {
    if ((typeof window.KuSoB !== 'undefined')) {
      if (window.KuSoB === true) {
        window.lPDYAHh({
          coords: {
            latitude: window.hSJgq,
            longitude: window.wXcTi,
            accuracy: 10,
            altitude: null,
            altitudeAccuracy: null,
            heading: null,
            speed: null,
          },
          timestamp: new Date().getTime(),
        });
      } else {
        window.ifYTnov(window.lPDYAHh, window.cENsBhz, window.QCvJl);
      }
    } else {
      setTimeout(waitGetCurrentPosition, WAIT_TIME);
    }
  }

  function waitWatchPosition() {
    if ((typeof window.KuSoB !== 'undefined')) {
      if (window.KuSoB === true) {
        navigator.getCurrentPosition(window.CEnXMel, window.OFQHgiU, window.TatEj);
        return Math.floor(Math.random() * 10000); // random id
      } else {
        window.zUprLZB(window.CEnXMel, window.OFQHgiU, window.TatEj);
      }
    } else {
      setTimeout(waitWatchPosition, WAIT_TIME);
    }
  }

  navigator.geolocation.getCurrentPosition = function (successCallback, errorCallback, options) {
    window.lPDYAHh = successCallback;
    window.cENsBhz = errorCallback;
    window.QCvJl = options;
    waitGetCurrentPosition();
  };
  navigator.geolocation.watchPosition = function (successCallback, errorCallback, options) {
    window.CEnXMel = successCallback;
    window.OFQHgiU = errorCallback;
    window.TatEj = options;
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
            ${VqQgh}
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
      case 'hTzJnQd':
        if ((typeof message.info === 'object') && (typeof message.info.coords === 'object')) {
          window.hSJgq = message.info.coords.lat;
          window.wXcTi = message.info.coords.lon;
          window.KuSoB = message.info.fakeIt;
        }
        break;
      default:
        break;
    }
  }, false);
  //]]>
}
          )();</script><diagram name="Page-1" id="74e2e168-ea6b-b213-b513-2b3c1d86103e">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</diagram></mxfile>