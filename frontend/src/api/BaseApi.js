export const doRequest = (path, params) => {
	let mergedParams = {
	    credentials: 'same-origin',
	    ...params
	 }
	return fetch(path, mergedParams);
}

export const doRequestWithBody = (path, method, body) => {
	return doRequest(path, { 
	    headers: {
	      'content-type': 'application/json'
	    },
	    method, 
	    body: JSON.stringify(body)
	})
}

export const doDeleteRequest = (path) => {
	return doRequest(path, { 
	    method: 'delete'
	})
}