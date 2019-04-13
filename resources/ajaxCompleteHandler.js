// USED: https://hashnode.com/post/is-there-any-way-to-intercept-ajax-call-responses-in-javascript-ciy14fnuy000bte53acydnlkl

XMLHttpRequest = class extends XMLHttpRequest {// by extending, we copy over all the original functionality

  send() { // we overwrite the send function

    // then we proxy the onreadystatechange property
    this._onreadystatechange = this.onreadystatechange;
    this.onreadystatechange = () => {

      // Do your stuff here!
      if (this.readyState == 4) {
      	console.log('Ready State: ' + this.responseURL);
      }
      if (typeof this._onreadystatechange === 'function') {

        this._onreadystatechange();// call original event handler
      }
    };

    super.send();// finally, we call the original send function
  };
};