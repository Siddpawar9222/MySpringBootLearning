import axios from "../customAxiosConfig/CustomAxiosConfig";

const getSupplierService = () => {
  try {
    return axios.get(`/api/supplier/searchSupplier?location=India&nature_of_business=small_scale&manufacturing_processes=casting&pageNumber=0&pageSize=5`);
  } catch (err) {
    console.error(err);
    throw err;
  }
};

export default getSupplierService ;